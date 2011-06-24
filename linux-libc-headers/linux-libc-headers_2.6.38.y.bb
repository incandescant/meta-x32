require linux-libc-headers.inc

INHIBIT_DEFAULT_DEPS = "1"
DEPENDS += "unifdef-native"

SRCREV = "81a169a5d4eeba0e626883ffdc5dd77d7114f68e"
PV = "2.6.38.y+git${SRCPV}"
PV = "2.6.38.y"
PR = "r0"

#SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/hjl/linux-2.6.38.y.git;protocol=git;nocheckout=1;branch=hjl/x32/master"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/hjl/linux-2.6.38.y.git;protocol=git;branch=hjl/x32/master"
SRC_URI += " file://connector-msg-size-fix.patch"

S = "${WORKDIR}/git"
B = "${S}"

# force this to empty to prevent installation failures, we aren't
# building a device tree as part of kern headers
KERNEL_DEVICETREE=

set_arch() {
	case ${TARGET_ARCH} in
		arm*)     ARCH=arm ;;
		i*86*)    ARCH=i386 ;;
		ia64*)    ARCH=ia64 ;;
		mips*)    ARCH=mips ;;
		powerpc*) ARCH=powerpc ;;
		x86_64*)  ARCH=x86_64 ;;
	esac
}

do_configure() {
	set_arch
	oe_runmake allnoconfig ARCH=$ARCH
}

do_compile () {
}

do_install() {
	set_arch
	oe_runmake headers_install INSTALL_HDR_PATH=${D}${exec_prefix} ARCH=$ARCH
	# Kernel should not be exporting this header
	rm -f ${D}${exec_prefix}/include/scsi/scsi.h
}

BBCLASSEXTEND = "nativesdk"
