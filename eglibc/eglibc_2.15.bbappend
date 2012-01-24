
THISDIR := "${@os.path.dirname(bb.data.getVar('FILE', d, True))}"
FILESPATH =. "${@base_set_filespath(["${THISDIR}/eglibc"], d)}:"

PR .= ".x32"
SRC_URI += "file://eglibc-2.15-x32.patch"

# The change here is just:
# -       cp ${S}/sysdeps/x86_64/bits/wordsize.h ${S}/sysdeps/i386/bits/wordsize.h
# +       cp ${S}/sysdeps/x86_64/64/bits/wordsize.h ${S}/sysdeps/i386/bits/wordsize.h
do_fix_ia_headers() {
	cp ${S}/sysdeps/unix/sysv/linux/x86_64/bits/a.out.h ${S}/sysdeps/unix/sysv/linux/i386/bits/a.out.h
	cp ${S}/sysdeps/x86_64/bits/byteswap.h ${S}/sysdeps/i386/bits/byteswap.h
	cp ${S}/sysdeps/x86_64/bits/endian.h ${S}/sysdeps/i386/bits/endian.h
	cp ${S}/sysdeps/unix/sysv/linux/x86_64/bits/environments.h ${S}/sysdeps/unix/sysv/linux/i386/bits/environments.h
	cp ${S}/sysdeps/unix/sysv/linux/x86_64/bits/fcntl.h ${S}/sysdeps/unix/sysv/linux/i386/bits/fcntl.h
	cp ${S}/sysdeps/x86_64/fpu/bits/fenv.h ${S}/sysdeps/i386/fpu/bits/fenv.h
	rm ${S}/sysdeps/i386/bits/huge_vall.h
	cp ${S}/sysdeps/x86_64/bits/link.h ${S}/sysdeps/i386/bits/link.h
	cp ${S}/sysdeps/x86_64/bits/mathdef.h ${S}/sysdeps/i386/bits/mathdef.h
	cp ${S}/sysdeps/x86_64/fpu/bits/mathinline.h ${S}/sysdeps/i386/fpu/bits/mathinline.h
	cp ${S}/sysdeps/unix/sysv/linux/x86_64/bits/mman.h ${S}/sysdeps/unix/sysv/linux/i386/bits/mman.h
	cp ${S}/sysdeps/unix/sysv/linux/x86_64/bits/msq.h ${S}/sysdeps/unix/sysv/linux/i386/bits/msq.h
	cp ${S}/nptl/sysdeps/unix/sysv/linux/x86_64/bits/pthreadtypes.h ${S}/nptl/sysdeps/unix/sysv/linux/i386/bits/pthreadtypes.h
	cp ${S}/sysdeps/x86_64/bits/select.h ${S}/sysdeps/i386/bits/select.h
	cp ${S}/nptl/sysdeps/unix/sysv/linux/x86_64/bits/semaphore.h ${S}/nptl/sysdeps/unix/sysv/linux/i386/bits/semaphore.h
	rm ${S}/sysdeps/unix/sysv/linux/x86_64/bits/sem.h
	cp ${S}/sysdeps/x86_64/bits/setjmp.h ${S}/sysdeps/i386/bits/setjmp.h
	cp ${S}/sysdeps/unix/sysv/linux/x86_64/bits/shm.h ${S}/sysdeps/unix/sysv/linux/i386/bits/shm.h
	cp ${S}/sysdeps/unix/sysv/linux/x86_64/bits/sigcontext.h ${S}/sysdeps/unix/sysv/linux/i386/bits/sigcontext.h
	cp ${S}/sysdeps/unix/sysv/linux/x86_64/bits/stat.h ${S}/sysdeps/unix/sysv/linux/i386/bits/stat.h
	rm ${S}/sysdeps/i386/i486/bits/string.h ; cp ${S}/sysdeps/x86_64/bits/string.h ${S}/sysdeps/i386/bits/string.h
	# Skip syscall.h, see do_install
	rm ${S}/sysdeps/unix/sysv/linux/i386/bits/wchar.h
	cp ${S}/sysdeps/x86_64/64/bits/wordsize.h ${S}/sysdeps/i386/bits/wordsize.h
	cp ${S}/sysdeps/x86_64/bits/xtitypes.h ${S}/sysdeps/i386/bits/xtitypes.h
	# i386 version is correct, x86_64 is incorrect for fpu_control.h
	cp ${S}/sysdeps/i386/fpu_control.h ${S}/sysdeps/x86_64/fpu_control.h
	cp ${S}/sysdeps/unix/sysv/linux/x86_64/sys/debugreg.h ${S}/sysdeps/unix/sysv/linux/i386/sys/debugreg.h
	cp ${S}/sysdeps/unix/sysv/linux/x86_64/sys/epoll.h ${S}/sysdeps/unix/sysv/linux/i386/sys/epoll.h
	cp ${S}/sysdeps/unix/sysv/linux/x86_64/sys/io.h ${S}/sysdeps/unix/sysv/linux/i386/sys/io.h
	cp ${S}/sysdeps/unix/sysv/linux/x86_64/sys/perm.h ${S}/sysdeps/unix/sysv/linux/i386/sys/perm.h
	cp ${S}/sysdeps/unix/sysv/linux/x86_64/sys/procfs.h ${S}/sysdeps/unix/sysv/linux/i386/sys/procfs.h
	cp ${S}/sysdeps/unix/sysv/linux/x86_64/sys/reg.h ${S}/sysdeps/unix/sysv/linux/i386/sys/reg.h
	cp ${S}/sysdeps/unix/sysv/linux/x86_64/sys/ucontext.h ${S}/sysdeps/unix/sysv/linux/i386/sys/ucontext.h
	cp ${S}/sysdeps/unix/sysv/linux/x86_64/sys/user.h ${S}/sysdeps/unix/sysv/linux/i386/sys/user.h
}
