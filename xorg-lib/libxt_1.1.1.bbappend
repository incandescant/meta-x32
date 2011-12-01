THISDIR := "${@os.path.dirname(bb.data.getVar('FILE', d, True))}"
FILESPATH =. "${@base_set_filespath(["${THISDIR}/files"], d)}:"

PR .= ".x32"

SRC_URI += "file://libXt-1.1.1-x32.patch"
