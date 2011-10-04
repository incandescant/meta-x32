require gcc-common.inc

PR = "r0"
PR_append = "+svn${SRCPV}"

# Third digit in PV should be incremented after a minor release
# happens from this branch on gcc e.g. currently its 4.6.0
# when 4.6.1 is releases and we bump SRCREV beyond the release
# on branch then PV should be incremented to 4.6.1+svnr${SRCPV}
# to reflect that change

PV = "4.7.0"

# BINV should be incremented after updating to a revision
# after a minor gcc release (e.g. 4.6.1 or 4.6.2) has been made
# the value will be minor-release+1 e.g. if current minor release was
# 4.6.1 then the value below will have 2 which will mean 4.6.2
# which will be next minor release and so on.

BINV = "4.7.0"

SRCREV = "177844"
BRANCH = "x32"
#FILESPATH = "${@base_set_filespath([ '${FILE_DIRNAME}/files' ], d)}"

DEPENDS =+ "mpfr gmp libmpc cloog ppl"
NATIVEDEPS = "mpfr-native gmp-native gettext-native libmpc-native cloog-native ppl-native"

LICENSE="GPL-3.0-with-GCC-exception & GPLv3"

LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552 \
                   file://COPYING3;md5=d32239bcb673463ab874e80d47fae504 \
                   file://COPYING3.LIB;md5=6a6a8e020838b23406c81b19c1d46df6 \
                   file://COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1 \
		   file://COPYING.RUNTIME;md5=fe60d87048567d4fe8c8a0ed2448bcc8"

SRC_URI = "svn://gcc.gnu.org/svn/gcc/branches/;module=${BRANCH};proto=http \
	   file://gcc-4.3.1-ARCH_FLAGS_FOR_TARGET.patch \
	   file://cache-amnesia.patch \
	   file://gcc-flags-for-build.patch \
           file://301-missing-execinfo_h.patch  \
           file://302-c99-snprintf.patch  \
           file://303-c99-complex-ugly-hack.patch  \
           file://304-index_macro.patch  \
           file://305-libmudflap-susv3-legacy.patch  \
           file://740-sh-pr24836.patch  \
           file://904-flatten-switch-stmt-00.patch  \
           file://zecke-xgcc-cpp.patch \
           file://gcc-poison-system-directories.patch \
           file://gcc-poison-dir-extend.patch \
           file://gcc-4.3.3-SYSROOT_CFLAGS_FOR_TARGET.patch \
	   file://optional_libstdc.patch \
	   file://disable_relax_pic_calls_flag.patch \
	   file://GPLUSPLUS_INCLUDE_DIR_with_sysroot.patch \
	   file://COLLECT_GCC_OPTIONS.patch \
           file://use-defaults.h-and-t-oe-in-B.patch \
	  "
#S = "${WORKDIR}/git"
S = "${TMPDIR}/work-shared/gcc-${PV}/x32"
B = "${WORKDIR}/git/build.${HOST_SYS}.${TARGET_SYS}"
 

SRC_URI[md5sum] = "93d1c436bf991564524701259b6285a2"
SRC_URI[sha256sum] = "23bd0013d76ac6fb4537e5e8f4e5947129362dcc32f0d08563b7d4d9e44c0e17"


# Language Overrides
FORTRAN = ""
JAVA = ""

#EXTRA_OECONF_BASE = "  --enable-cheaders=c_std \
#			--enable-libssp \
#			--disable-bootstrap \
#			--disable-libgomp \
#			--disable-libmudflap"
EXTRA_OECONF_BASE = " --enable-lto \
			--enable-libssp \
			--disable-bootstrap \
			--disable-libgomp \
			--disable-libmudflap \
			--with-linker-hash-style=${LINKER_HASH_STYLE} \
			--enable-cheaders=c_global \
			--enable-x32 "

EXTRA_OECONF_INITIAL = "--disable-libmudflap \
			--disable-libgomp \
			--disable-libssp \
			--disable-libquadmath \
			--enable-decimal-float=no \
			--enable-x32 "


EXTRA_OECONF_INTERMEDIATE = "--disable-libmudflap \
				--disable-libgomp \
				--disable-libquadmath \
				--disable-libssp \
				--enable-x32 "

EXTRA_OECONF_append_linux-uclibc = " --disable-decimal-float "
EXTRA_OECONF_append_linux-uclibceabi = " --disable-decimal-float "
EXTRA_OECONF_append_linux-uclibcspe = " --disable-decimal-float "
