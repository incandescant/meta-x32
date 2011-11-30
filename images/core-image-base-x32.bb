#
# Copyright (C) 2011 Intel Corporation.
#

DESCRIPTION = "Base x32 Linux Image"
DEPENDS = "linux-korg"

require recipes-core/images/core-image-base.bb

IMAGE_INSTALL += "file"

LICENSE = "MIT"
