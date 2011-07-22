#
# For now, we will skip building of a gcc package if it is a uclibc one
# and our build is not a uclibc one, and we skip a glibc one if our build
# is a uclibc build.
#
# See the note in gcc/gcc_3.4.0.oe
#

python __anonymous () {
    import bb, re
    uc_os = (re.match('.*uclibc*', bb.data.getVar('TARGET_OS', d, 1)) != None)
    if uc_os:
        raise bb.parse.SkipPackage("incompatible with target %s" %
                                   bb.data.getVar('TARGET_OS', d, 1))
}

PKGSUFFIX = ""
PKGSUFFIX_virtclass-nativesdk = "-nativesdk"

PACKAGES = "${PN}-dbg ${PN} catchsegv${PKGSUFFIX} sln${PKGSUFFIX} nscd${PKGSUFFIX} \
	ldd${PKGSUFFIX} ${PN}-utils ${PN}-dev ${PN}-doc \
	libsegfault${PKGSUFFIX} ${PN}-extra-nss ${PN}-thread-db \
	${PN}-pcprofile"

libc_baselibs = "${base_libdir}/libc* ${base_libdir}/libm* ${base_libdir}/ld* \
	${base_libdir}/libpthread* ${base_libdir}/libresolv* ${base_libdir}/librt* \
	${base_libdir}/libutil* ${base_libdir}/libnsl* ${base_libdir}/libnss_files* \
	${base_libdir}/libnss_compat* ${base_libdir}/libnss_dns* ${base_libdir}/libdl* \
	${base_libdir}/libanl* ${base_libdir}/libBrokenLocale*"

# The problem is that if PN = "glibc", FILES_${PN} will overwrite FILES_glibc
# Solution: Make them both the same thing, then it doesn't matter

glibcfiles = "${sysconfdir} ${libc_baselibs} ${base_sbindir}/ldconfig \
	${libexecdir}/* ${datadir}/zoneinfo"
glibcdbgfiles = "${bindir}/.debug ${sbindir}/.debug ${libdir}/.debug \
                  ${base_bindir}/.debug ${base_sbindir}/.debug ${base_libdir}/.debug \
                  ${libdir}/gconv/.debug ${libexecdir}/*/.debug"
glibcdevfiles = "${bindir}/rpcgen ${includedir} ${libdir}/lib*${SOLIBSDEV} ${libdir}/*.la \
                ${libdir}/*.a ${libdir}/*.o ${libdir}/pkgconfig \
                ${base_libdir}/*.a ${base_libdir}/*.o ${datadir}/aclocal"

FILES_glibc = "${glibcfiles}"
FILES_${PN} = "${glibcfiles}"
FILES_ldd${PKGSUFFIX} = "${bindir}/ldd"
FILES_libsegfault${PKGSUFFIX} = "${base_libdir}/libSegFault*"
FILES_glibc-extra-nss = "${base_libdir}/libnss_*-*.so ${base_libdir}/libnss_*.so.*"
FILES_sln = "${base_sbindir}/sln"
FILES_glibc-dev = "${glibcdevfiles}"
FILES_${PN}-dev = "${glibcdevfiles}"
FILES_glibc-dbg = "${glibcdbgfiles}"
FILES_${PN}-dbg = "${glibcdbgfiles}"
FILES_nscd${PKGSUFFIX} = "${sbindir}/nscd* ${sysconfdir}/nscd* ${sysconfdir}/init.d/nscd*"
FILES_glibc-utils = "${bindir}/* ${sbindir}/*"
RDEPENDS_glibc-utils += "bash"
FILES_glibc-gconv = "${libdir}/gconv/*"
FILES_catchsegv${PKGSUFFIX} = "${bindir}/catchsegv"
RDEPENDS_catchsegv${PKGSUFFIX} = "libsegfault"
FILES_glibc-pcprofile = "${base_libdir}/libpcprofile.so"
FILES_glibc-thread-db${PKGSUFFIX} = "${base_libdir}/libthread_db.so.* ${base_libdir}/libthread_db-*.so"

RPROVIDES_glibc-dev += "libc-dev"

SUMMARY_sln = "The static ln."
DESCRIPTION_sln = "Similar to the 'ln' utility, but statically linked.  sln is useful to make symbolic links to dynamic libraries if the dynamic linking system, for some reason, is not functional."
SUMMARY_nscd = "Name service cache daemon"
DESCRIPTION_nscd = "nscd, name service cache daemon, caches name service lookups for the passwd, group and hosts information.  It can damatically improvide performance with remote, such as NIS or NIS+, name services."
SUMMARY_glibc-extra-nss = "hesiod, NIS and NIS+ nss libraries"
DESCRIPTION_glibc-extra-nss = "glibc: nis, nisplus and hesiod search services.
SUMMARY_ldd = "print shared library dependencies"
DESCRIPTION_ldd = "/usr/bin/ldd prints shared library dependencies for each program or shared library specified on the command line."
SUMMARY_glibc-utils = "Miscellaneous utilities provided by glibc"
DESCRIPTION_glibc-utils = "Miscellaneous utilities including getconf, iconf, locale, gencat, tzselect, zic, rpcinfo, ..."

EXTRA_OEMAKE += "rootsbindir=${base_sbindir}"

inherit libc-common multilib_header

do_install_append () {
       rm -f ${D}${sysconfdir}/localtime
#       oe_multilib_header bits/syscall.h
}

do_install_locale () {
	dest=${D}/${includedir}/glibc-locale-internal-${MULTIMACH_TARGET_SYS}
	install -d ${dest}${base_libdir} ${dest}${bindir} ${dest}${libdir} ${dest}${datadir}
        if [ "${base_libdir}" != "${libdir}" ]; then
                cp -fpPR ${D}${base_libdir}/* ${dest}${base_libdir}
        fi
        if [ -f ${D}${bindir}/localedef ]; then
                mv -f ${D}${bindir}/localedef ${dest}${bindir}
        fi
        if [ -f ${D}${libdir}/gconv ]; then
                mv -f ${D}${libdir}/gconv ${dest}${libdir}
        fi
	cp -fpPR ${D}${libdir}/* ${dest}${libdir}
        mv ${D}${datadir}/i18n ${dest}${datadir}
	cp -fpPR ${D}${datadir}/* ${dest}${datadir}
	cp -fpPR ${WORKDIR}/SUPPORTED ${dest}
}

addtask do_install_locale after do_install before do_populate_sysroot do_package

PACKAGE_PREPROCESS_FUNCS += "glibc_package_preprocess"

glibc_package_preprocess () {
	rm -rf ${PKGD}/${includedir}/eglibc-locale-internal-${MULTIMACH_TARGET_SYS}
}

