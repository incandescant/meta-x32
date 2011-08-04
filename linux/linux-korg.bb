inherit kernel
require recipes-kernel/linux/linux-yocto.inc

#KMACHINE = "yocto/standard/auto-bsp"
KMACHINE = "x32"
#YOCTO_KERNEL_EXTERNAL_BRANCH ?= "yocto/standard/auto-bsp"
YOCTO_KERNEL_EXTERNAL_BRANCH="hjl/x32/master"

KBRANCH = ${KMACHINE}
KMETA = meta

#SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux-2.6.git;protocol=git;nocheckout=1"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/hjl/linux-2.6.38.y.git;nocheckout=1;branch=hjl/x32/master \
            file://defconfig"

SRCREV=${AUTOREV}
# SRCREV_pn-linux-korg = 

#LINUX_VERSION ?= "2.6.39"
LINUX_VERSION = "2.6.38.y"
KERNEL_REVISION_CHECKING=
LOCALCOUNT = "0"
LINUX_VERSION_EXTENSION ?= "-yoctized-${LINUX_KERNEL_TYPE}"
PR = "r0"
PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE = "(qemuarm|qemux86|qemuppc|qemumips|qemux86-64)"

# Functionality flags
KERNEL_REVISION_CHECKING=

# extra tasks
addtask kernel_link_vmlinux after do_compile before do_install
addtask validate_branches before do_patch after do_kernel_checkout
addtask kernel_configcheck after do_configure before do_compile

#require recipes-kernel/linux/linux-tools.inc
