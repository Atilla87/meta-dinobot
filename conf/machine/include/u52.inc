EXTRA_OECONF_append_pn-enigma2 = "--with-lcddev=/dev/null --with-alphablendingacceleration=always"

SOC_FAMILY = "hisi3798mv200"

KERNEL_MODULE_AUTOLOAD += "8192eu"

require conf/machine/include/dinobot-cortexa15.inc
require conf/machine/include/dinobot-emmc.inc

PREFERRED_PROVIDER_virtual/blindscan-dvbs = "dinobot-blindscan-u52"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "\
    dinobot-dvb-modules-u52 \
    dinobot-libs-u52 \
    dinobot-hihalt-u52 \
"

PREFERRED_RPROVIDER_showiframe = "dinobot-showiframe-u52"
PREFERRED_RPROVIDER_aio-grab = "dinobot-grab-u52"

MACHINETYPE = "u52"

MTD_KERNEL = "mmcblk0p10"
MTD_ROOTFS = "mmcblk0p13"

SDCARDIMG = "no"

MACHINE_FEATURES += "7segment dvb-c blindscan-dvbs textlcd"
