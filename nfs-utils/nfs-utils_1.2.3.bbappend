THISDIR := "${@os.path.dirname(bb.data.getVar('FILE', d, True))}"
FILESPATH =. "${@base_set_filespath(["${THISDIR}/files"], d)}:"

PR .= ".x32"

SRC_URI += "file://nfs-utils-nfsctl-fix.patch"
