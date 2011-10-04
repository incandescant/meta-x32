require glibc.inc

SRCREV = "30ab7bbaa8d31b5ce674c9efb33d524a2301b62c"
PV = "2.14"
PR = "r1"
PV_append = "+git${SRCPV}"

DEPENDS += "gperf-native"
FILESPATHPKG =. "glibc-git:"

ARM_INSTRUCTION_SET = "arm"

PACKAGES_DYNAMIC = "libc6*"
RPROVIDES_${PN}-dev = "libc6-dev virtual-libc-dev"

# the -isystem in bitbake.conf screws up glibc do_stage
BUILD_CPPFLAGS = "-I${STAGING_INCDIR_NATIVE}"
TARGET_CPPFLAGS = "-I${STAGING_DIR_TARGET}${includedir}"

GLIBC_ADDONS ?= "ports,nptl,libidn"

GLIBC_BROKEN_LOCALES = " _ER _ET so_ET yn_ER sid_ET tr_TR mn_MN gez_ET gez_ER bn_BD te_IN wae_CH "

FILESPATH = "${@base_set_filespath([ '${FILE_DIRNAME}/glibc-${PV}', '${FILE_DIRNAME}/glibc-2.4', '${FILE_DIRNAME}/glibc', '${FILE_DIRNAME}/files', '${FILE_DIRNAME}' ], d)}"

#
# For now, we will skip building of a gcc package if it is a uclibc one
# and our build is not a uclibc one, and we skip a glibc one if our build
# is a uclibc build.
#
# See the note in gcc/gcc_3.4.0.oe
#

python __anonymous () {
    import bb, re
    uc_os = (re.match('.*uclibc$', bb.data.getVar('TARGET_OS', d, 1)) != None)
    if uc_os:
        raise bb.parse.SkipPackage("incompatible with target %s" %
                                   bb.data.getVar('TARGET_OS', d, 1))
}

RDEPENDS_${PN}-dev = "linux-libc-headers-dev"


SRC_URI = " \
	   git://github.com/hjl-tools/glibc.git;protocol=git;branch=hjl/x32/lfs/master \
	   http://pkgs.fedoraproject.org/repo/pkgs/glibc/glibc-ports-2.14.tar.xz/05c85905b43021a81318c3aa81718019/glibc-ports-2.14.tar.xz \
           file://shorten-build-commands.patch \
           file://etc/ld.so.conf \
           file://generate-supported.mk \
	   file://nptl-crosscompile.patch \
	   file://fix_library_path.patch \
	   "
#           file://arch-ia32.patch 

SRC_URI[md5sum] = "05c85905b43021a81318c3aa81718019"
SRC_URI[sha256sum] = "3691677a855fd5caf4c90ff922c132a7d2b966279a342733860b0c9084a155d9"


LIC_FILES_CHKSUM = "file://LICENSES;md5=98a1128c4b58120182cbea3b1752d8b9 \
      file://COPYING;md5=393a5ca445f6965873eca0259a17f833 \
      file://posix/rxspencer/COPYRIGHT;md5=dc5485bb394a13b2332ec1c785f5d83a \
      file://COPYING.LIB;md5=bbb461211a33b134d42ed5ee802b37ff "

SRC_URI_append_virtclass-nativesdk = " file://ld-search-order.patch"
S = "${WORKDIR}/git"
B = "${WORKDIR}/build-${TARGET_SYS}"

# We need this for nativesdk
export libc_cv_slibdir = "${base_libdir}"
export libc_cv_forced_unwind = "yes"
export libc_cv_c_cleanup = "yes"
export ac_cv_header_cpuid_h = "yes"
export libc_cv_ctors_header = "yes"
export libc_cv_gcc_builtin_expect = "yes"

EXTRA_OECONF = "--enable-kernel=${OLDEST_KERNEL} \
                --without-cvs --disable-profile --disable-debug --without-gd \
                --enable-clocale=gnu \
                --enable-add-ons=${GLIBC_ADDONS},ports \
                --with-headers=${STAGING_INCDIR} \
                --without-selinux \
                ${GLIBC_EXTRA_OECONF}"

EXTRA_OECONF += "${@get_libc_fpu_setting(bb, d)}"

do_unpack_append() {
	bb.build.exec_func('do_move_ports', d)
}

do_move_ports() {
        if test -d ${WORKDIR}/glibc-ports-${PV} ; then
	    rm -rf ${S}/ports
	    mv ${WORKDIR}/glibc-ports-${PV} ${S}/ports
	fi
}

do_configure () {
# /var/db was not included to FHS
	sed -i s:/var/db/nscd:/var/run/nscd: ${S}/nscd/nscd.h
# override this function to avoid the autoconf/automake/aclocal/autoheader
# calls for now
# don't pass CPPFLAGS into configure, since it upsets the kernel-headers
# version check and doesn't really help with anything
        if [ -z "`which rpcgen`" ]; then
                echo "rpcgen not found.  Install glibc-devel."
                exit 1
        fi
        (cd ${S} && gnu-configize) || die "failure in running gnu-configize"
        find ${S} -name "configure" | xargs touch
        CPPFLAGS="" oe_runconf
}

rpcsvc = "bootparam_prot.x nlm_prot.x rstat.x \
	  yppasswd.x klm_prot.x rex.x sm_inter.x mount.x \
	  rusers.x spray.x nfs_prot.x rquota.x key_prot.x"

do_compile () {
	# -Wl,-rpath-link <staging>/lib in LDFLAGS can cause breakage if another glibc is in staging
	unset LDFLAGS
	base_do_compile
	(
		cd ${S}/sunrpc/rpcsvc
		for r in ${rpcsvc}; do
			h=`echo $r|sed -e's,\.x$,.h,'`
			rpcgen -h $r -o $h || bbwarn "unable to generate header for $r"
		done
	)
}

require glibc-stage.inc

require glibc-package.inc

BBCLASSEXTEND = "nativesdk"
