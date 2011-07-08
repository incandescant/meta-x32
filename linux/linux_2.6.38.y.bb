require linux.inc
PROVIDES = "kernel-base"
PROVIDES += "virtual/kernel"

PR = "r0"

SRCREV = "81a169a5d4eeba0e626883ffdc5dd77d7114f68e"
#git://localhost/home/nitin/prj/x32/linux-2.6.38.y.git;protocol=file;branch=x32
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/hjl/linux-2.6.38.y.git;branch=hjl/x32/master \
           file://defconfig "

SRC_URI += " file://fix_for_gcc_4.7.patch"

LIC_FILES_CHKSUM="file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
S = ${WORKDIR}/git

#COMPATIBLE_MACHINE = "qemux86-64"
#TARGET_CC_ARCH = "-m64"
