DESCRIPTION = "gdb - GNU debugger"
HOMEPAGE = "http://www.gnu.org/software/gdb/"
LICENSE="GPLv3+"
SECTION = "devel"
PRIORITY = "optional"
DEPENDS = "ncurses readline elfutils"

LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552 \
		file://COPYING.LIB;md5=9f604d8a4f8e74f4f5140845a21b6674 \
		file://COPYING3;md5=d32239bcb673463ab874e80d47fae504 \
		file://COPYING3.LIB;md5=6a6a8e020838b23406c81b19c1d46df6"
inherit autotools

SRCREV = "2d7ed8879130e980f3acd3c3d3e803302948ef94"
PV = "7.3.50+git${SRCPV}"
PV = "7.3.50"
PR = "r0"

SRC_URI = " git://git.kernel.org/pub/scm/devel/gdb/hjl/x86.git;protocol=git;branch=hjl/x32 \
           file://no-werror.patch"

export CC_FOR_BUILD = "${BUILD_CC}"
export CXX_FOR_BUILD = "${BUILD_CXX}"
export CPP_FOR_BUILD = "${BUILD_CPP}"
export CFLAGS_FOR_BUILD = "${BUILD_CFLAGS}"
export CXXFLAGS_FOR_BUILD = "${BUILD_CXXFLAGS}"
export CPPFLAGS_FOR_BUILD = "${BUILD_CPPFLAGS}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build-${TARGET_SYS}"

EXTRA_OEMAKE = "'SUBDIRS=intl mmalloc libiberty opcodes bfd sim gdb etc utils'"

EXPAT = "--without-expat"

EXTRA_OECONF = "--disable-gdbtk --disable-tui --disable-x \
                --with-curses --disable-multilib --with-system-readline --disable-sim \
                ${GDBPROPREFIX} --with-libelf=${STAGING_DIR_TARGET} ${EXPAT} \
                --without-ust \
               "

GDBPROPREFIX = "--program-prefix=''"

do_configure () {
	# override this function to avoid the autoconf/automake/aclocal/autoheader
	# calls for now
	(cd ${S} && gnu-configize) || die "failure in running gnu-configize"
	oe_runconf
}

# we don't want gdb to provide bfd/iberty/opcodes, which instead will override the
# right bits installed by binutils.
do_install_append() {
	rm -rf ${D}${libdir}
	rm -rf ${D}${includedir}
	rm -rf ${D}${datadir}/locale
}

RRECOMMENDS_gdb_append_linux = " glibc-thread-db "
RRECOMMENDS_gdb_append_linux-gnueabi = " glibc-thread-db "

