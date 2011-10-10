How to build an x32 linux image

1. Add this meta-x32 layer to your build layer config file
	conf/bblayers.conf
  
2. Add these lines to your conf/local.conf:

MACHINE = "qemux86-64"
DEFAULTTUNE = "x86-64-x32"
baselib = "${@d.getVar('BASE_LIB_tune-' + (d.getVar('DEFAULTTUNE', True) or
'INVALID'), True) or 'lib'}"

3. build
	bitbake core-image-minimal-x32

4. boot the image
	runqemu qemux86-64 core-image-minimal-x32

5. after looging into the image verify that the executables & libraries are x32
	file /bin/busybox
	file /libx32/libc.so.*

	If file command should reports that binaries are one of these types
		"ELF 32-bit LSB executable, x86-64"
		"ELF 32-bit LSB shared onject, x86-64"



Nitin A Kamble <nitin.a.kamble@intel.com>
2011-10-10
