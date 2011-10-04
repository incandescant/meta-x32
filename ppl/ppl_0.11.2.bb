SUMMARY = "The Parma Polyhedra Library: a library of numerical abstractions"
DESCRIPTION = "The Parma Polyhedra Library (PPL) is a library for the \
manipulation of (not necessarily closed) convex polyhedra and other numerical \
abstractions.  The applications of convex polyhedra include program \
analysis, optimized compilation, integer and combinatorial \
optimization and statistical data-editing.  The Parma Polyhedra \
Library comes with several user friendly interfaces, is fully dynamic \
(available virtual memory is the only limitation to the dimension of \
anything), written in accordance to all the applicable standards, \
exception-safe, rather efficient, thoroughly documented, and free \
software.  This package provides all what is necessary to run \
applications using the PPL through its C and C++ interfaces."
HOMEPAGE = "http://www.cs.unipr.it/ppl/"
SECTION = "libs"
LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
DEPENDS = "gmp"

PR = "r0"

SRC_URI = "ftp://ftp.cs.unipr.it/pub/ppl/releases/0.11.2/ppl-0.11.2.tar.gz"

SRC_URI[md5sum] = "ce014f153a28006009db207ca953a984"
SRC_URI[sha256sum] = "666542b853beeb5793cd327fff8f231a8317e162654e817f441427fe1f85b835"

inherit autotools
acpaths = "-I ${S}/m4"

do_configure_prepend () {
        mv aclocal.m4 acinclude.m4
        mv Watchdog/aclocal.m4 Watchdog/acinclude.m4
}

BBCLASSEXTEND = "native nativesdk"
