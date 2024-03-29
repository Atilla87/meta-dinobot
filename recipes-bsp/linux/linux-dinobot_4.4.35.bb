DESCRIPTION = "Linux kernel for ${MACHINE}"
SECTION = "kernel"
LICENSE = "GPLv2"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "^(anadol4k|axashis4kcombo|axashis4kcomboplus|dinobot4k|dinobot4kl|dinobot4kmini|dinobot4kplus|dinobot4kse|mediabox4k|ferguson4k|anadol4kcombo|anadol4kv2|dinobot4kpro|dinobotu55|dinoboth265|axashistwin|axashisc4k|anadolprohd5|dinobotu43|protek4kx1|spycatminiv2|iziboxecohd|jdhdduo|turing|arivacombo|arivatwin|iziboxx3)$"

KERNEL_RELEASE = "4.4.35"
SRCDATE = "20180828"
SRCDATE_dinobot4kse = "20180502"
SRCDATE_ferguson4k = "20180502"

inherit kernel machine_kernel_pr

SRC_URI[newgeneration.md5sum] = "f0dd43d5adc013d0dd89061e3249855a"
SRC_URI[newgeneration.sha256sum] = "32a8caabfba94d81b649de8dd62cc5b02e1d750cad8d2676e98e242a944273a3"

SRC_URI[dinobot4kse.md5sum] = "bd22f82d08a5feb4f1360d5739919ee0"
SRC_URI[dinobot4kse.sha256sum] = "df83207ddfe34ac41a55e5e42eaae9c3ac3c4ef0750c786886719a33bf08b617"

SRC_URI[ferguson4k.md5sum] = "bd22f82d08a5feb4f1360d5739919ee0"
SRC_URI[ferguson4k.sha256sum] = "df83207ddfe34ac41a55e5e42eaae9c3ac3c4ef0750c786886719a33bf08b617"

LIC_FILES_CHKSUM = "file://${WORKDIR}/linux-${PV}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

# By default, kernel.bbclass modifies package names to allow multiple kernels
# to be installed in parallel. We revert this change and rprovide the versioned
# package names instead, to allow only one kernel to be installed.
PKG_${KERNEL_PACKAGE_NAME}-base = "kernel-base"
PKG_${KERNEL_PACKAGE_NAME}-image = "kernel-image"
RPROVIDES_${KERNEL_PACKAGE_NAME}-base = "kernel-${KERNEL_VERSION}"
RPROVIDES_${KERNEL_PACKAGE_NAME}-image = "kernel-image-${KERNEL_VERSION}"

SRC_URI = "http://source.mynonpublic.com/dinobot/dinobot-linux-${PV}-${SRCDATE}.tar.gz;name=newgeneration \
    file://defconfig \
    file://410dts.patch \
    file://0001-mmc-switch-1.8V.patch \
    file://wifi-linux_4.4.183.patch \
    file://0002-log2-give-up-on-gcc-constant-optimizations.patch \
    file://0003-uaccess-dont-mark-register-as-const.patch \
    file://0004-makefile-disable-warnings.patch \
    file://0005-kallsyms-allow-bigger-ksym_name_len.patch \
"

SRC_URI_dinobot4kse = "http://source.mynonpublic.com/dinobot/dinobot-linux-${PV}-${SRCDATE}.tar.gz;name=${MACHINE} \
    file://defconfig \
    file://sdio-platform.patch \
    file://accelmem.patch \
    file://cma.patch \
    file://ahci-clock.patch \
    ${@bb.utils.contains('SOC_FAMILY', 'hisi3798mv200', 'file://led.patch' , '', d)} \
    file://0002-log2-give-up-on-gcc-constant-optimizations.patch \
    file://0003-uaccess-dont-mark-register-as-const.patch \
    file://0004-makefile-disable-warnings.patch \
    file://0005-kallsyms-allow-bigger-ksym_name_len.patch \
"

SRC_URI_ferguson4k = "http://source.mynonpublic.com/dinobot/dinobot-linux-${PV}-${SRCDATE}.tar.gz;name=${MACHINE} \
    file://defconfig \
    file://sdio-platform.patch \
    file://accelmem.patch \
    file://cma.patch \
    file://ahci-clock.patch \
    ${@bb.utils.contains('SOC_FAMILY', 'hisi3798mv200', 'file://led.patch' , '', d)} \
    file://0002-log2-give-up-on-gcc-constant-optimizations.patch \
    file://0003-uaccess-dont-mark-register-as-const.patch \
    file://0004-makefile-disable-warnings.patch \
    file://0005-kallsyms-allow-bigger-ksym_name_len.patch \
"

S = "${WORKDIR}/linux-${PV}"
B = "${WORKDIR}/build"

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_IMAGEDEST = "tmp"

FILES_${KERNEL_PACKAGE_NAME}-image = "${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}"

KERNEL_IMAGETYPE = "uImage"
KERNEL_OUTPUT = "arch/${ARCH}/boot/${KERNEL_IMAGETYPE}"

kernel_do_install_append() {
	install -d ${D}/${KERNEL_IMAGEDEST}
	install -m 0755 ${KERNEL_OUTPUT} ${D}/${KERNEL_IMAGEDEST}
	if [ -e "${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/msp/drv/frontend/hi_tuner.ko" ]; then
		rm -f ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/msp/drv/frontend/hi_tuner.ko
	fi
}

do_rm_work() {
}
