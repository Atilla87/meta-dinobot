KV = "4.4.35"
SRCDATE = "20190718"

do_install_append() {
	install -d ${D}/lib/firmware/brcm
	install -d ${D}/${sysconfdir}/modules-load.d
	install -m 0644 ${WORKDIR}/brcmfmac43455-sdio.bin ${D}/lib/firmware/brcm
	install -m 0644 ${WORKDIR}/brcmfmac43455-sdio.txt ${D}/lib/firmware/brcm
	install -m 0644 ${WORKDIR}/*.ko ${D}${nonarch_base_libdir}/modules/${KV}/extra
	install -m 0644 ${WORKDIR}/*.conf ${D}/${sysconfdir}/modules-load.d
}

FILES_${PN} += "/lib/firmware/brcm"

require dinobot-dvb-modules2.inc

SRC_URI += "file://u53x-platform.zip file://dinobot-sdio-wifi.zip"

SRC_URI[md5sum] = "f5f93cbbb4b489aa59902a03c41c868b"
SRC_URI[sha256sum] = "9875c3598c99049d95886ad0c724970ce07e078a7a8a9264ea8505bfa4fa35de"

COMPATIBLE_MACHINE = "arivatwin"
