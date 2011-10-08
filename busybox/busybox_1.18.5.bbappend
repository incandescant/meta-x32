THISDIR := "${@os.path.dirname(bb.data.getVar('FILE', d, True))}"
FILESPATH =. "${@base_set_filespath(["${THISDIR}/files"], d)}:"

SRC_URI += "file://fix_build_with_glibc_2.14.patch"

PR .= ".x32"
