THISDIR := "${@os.path.dirname(bb.data.getVar('FILE', d, True))}"
FILESPATH =. "${@base_set_filespath(["${THISDIR}/glib-2.0"], d)}:"

SRC_URI += " file://x32_compile_fix.patch"

PR .= ".x32"
