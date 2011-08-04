#
# Copyright (C) 2010 Intel Corporation.
#

DESCRIPTION = "Minimal x32 Linux Image"
DEPENDS = "linux-korg"

require recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL += "file"

LICENSE = "MIT"
