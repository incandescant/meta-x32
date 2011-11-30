THISDIR := "${@os.path.dirname(bb.data.getVar('FILE', d, True))}"
FILESPATH =. "${@base_set_filespath(["${THISDIR}/gcc-4.6"], d)}:"

PR .= ".x32"

EXTRA_OECONF_BASE += "--with-multilib-list=m64,mx32"
EXTRA_OECONF_INITIAL += "--with-multilib-list=m64,mx32"
EXTRA_OECONF_INTERMEDIATE += "--with-multilib-list=m64,mx32"
