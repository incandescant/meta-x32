
THISDIR := "${@os.path.dirname(bb.data.getVar('FILE', d, True))}"
FILESPATH =. "${@base_set_filespath(["${THISDIR}/gmp"], d)}:"

PR .= ".x32"
SRC_URI += "file://gmp-x32-1.patch"
