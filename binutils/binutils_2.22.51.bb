require binutils.inc

PR = "r2"

LIC_FILES_CHKSUM="\
    file://src-release;endline=17;md5=4830a9ef968f3b18dd5e9f2c00db2d35\
    file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552\
    file://COPYING.LIB;md5=9f604d8a4f8e74f4f5140845a21b6674\
    file://COPYING3;md5=d32239bcb673463ab874e80d47fae504\
    file://COPYING3.LIB;md5=6a6a8e020838b23406c81b19c1d46df6\
    file://gas/COPYING;md5=d32239bcb673463ab874e80d47fae504\
    file://include/COPYING;md5=59530bdf33659b29e73d4adb9f9f6552\
    file://include/COPYING3;md5=d32239bcb673463ab874e80d47fae504\
    file://libiberty/COPYING.LIB;md5=a916467b91076e631dd8edb7424769c7\
    file://bfd/COPYING;md5=d32239bcb673463ab874e80d47fae504\
    "

SRC_URI = "\
     ftp://sourceware.org/pub/binutils/snapshots/binutils-${PV}.tar.bz2 \
     file://binutils-x86_64_i386_biarch.patch \
     file://libtool-2.4-update.patch \
     file://binutils-2.19.1-ld-sysroot.patch \
     file://libiberty_path_fix.patch \
     file://binutils-poison.patch \
     file://libtool-rpath-fix.patch \
     file://clone-shadow.patch \
     "

SRC_URI[md5sum] = "2844618ac2ea0f4d8d1bcbe152ab0dd3"
SRC_URI[sha256sum] = "97b2911ee280a5017bb26b285b826bce8c3a1b92e4f1af068ba0cac9bf4e5e8a"

BBCLASSEXTEND = "native"