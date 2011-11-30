THISDIR := "${@os.path.dirname(bb.data.getVar('FILE', d, True))}"
FILESPATH =. "${@base_set_filespath(["${THISDIR}/files"], d)}:"

PR .= ".x32"

SRC_URI += "file://Mesa-7.11-x32.patch"
