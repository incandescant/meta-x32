#
# Copyright (C) 2011 Intel Corporation.
#

DESCRIPTION = "Base x32 Linux Image"
DEPENDS = "linux-korg"

require recipes-core/images/core-image-core.bb

IMAGE_INSTALL += "file"
IMAGE_FEATURES += "ssh-server-openssh"

LICENSE = "MIT"
