inherit kernel
require recipes-kernel/linux/linux-yocto.inc

KMACHINE = "x32"
YOCTO_KERNEL_EXTERNAL_BRANCH="hjl/x32/lfs/v3.1"
YOCTO_KERNEL_META_DATA=

KBRANCH = ${KMACHINE}
KMETA = meta

SRC_URI = "git://github.com/hjl-tools/linux.git;nocheckout=1;branch=hjl/x32/lfs/v3.1 \
            file://defconfig"

SRCREV=${AUTOREV}
SRCREV="e2bf8464ddbf5da24d3d320cded5691828a91a0b"
# SRCREV_pn-linux-korg = 

LINUX_VERSION = "3.1"
KERNEL_REVISION_CHECKING=
LOCALCOUNT = "0"
LINUX_VERSION_EXTENSION ?= "-yoctized-${LINUX_KERNEL_TYPE}"
PR = "r1"
PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE = "(qemuarm|qemux86|qemuppc|qemumips|qemux86-64)"

# Functionality flags
KERNEL_REVISION_CHECKING=

# extra tasks
addtask kernel_link_vmlinux after do_compile before do_install
addtask validate_branches before do_patch after do_kernel_checkout
addtask kernel_configcheck after do_configure before do_compile

#require recipes-kernel/linux/linux-tools.inc
