SUMMARY = "The dynamic shared libraries of the Chunky Loop Generator"
DESCRIPTION = "CLooG is a free software and library to generate code for \
scanning Z-polyhedra. That is, it finds a code (e.g. in C, FORTRAN...) that \
reaches each integral point of one or more parameterized polyhedra. CLooG has \
been originally written to solve the code generation problem for optimizing \
compilers based on the polytope model. Nevertheless it is used now in various \
area e.g. to build control automata for high-level synthesis or to find the \
best polynomial approximation of a function. CLooG may help in any situation \
where scanning polyhedra matters. While the user has full control on \
generated code quality, CLooG is designed to avoid control overhead and to \
produce a very effective code."
HOMEPAGE = "http://www.cloog.org/"
SECTION = "libs"
LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://source/cloog.c;beginline=15;endline=30;md5=840f2853e933d213a6431e3d28899396"
DEPENDS = "gmp"

PR = "r0"

SRC_URI = "http://www.bastoul.net/cloog/pages/download/cloog-${PV}.tar.gz"

SRC_URI[md5sum] = "83877caaa879c7160063138bb18348e7"
SRC_URI[sha256sum] = "bd9a9aca06aa324e61071cef19f828e08444df847b2dcebd2b66a280354d36f1"

inherit autotools

do_configure_prepend () {
	set -x
        mv aclocal.m4 acinclude.m4
        mv isl/aclocal.m4 isl/acinclude.m4
}


BBCLASSEXTEND = "native nativesdk"
