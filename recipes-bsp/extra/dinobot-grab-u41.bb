SUMMARY = "grab for dinobot Model ${MACHINE}"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "CLOSED"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "dinoboth265|axashistwin"

SRCDATE = "20190506"

PV = "${SRCDATE}"

RPROVIDES_${PN}  = "aio-grab"
RREPLACES_${PN}  = "aio-grab"
RCONFLICTS_${PN} = "aio-grab"

SRC_URI = "http://source.mynonpublic.com/dinobot/u41-grab-${SRCDATE}.tar.gz"

SRC_URI[md5sum] = "58748ed2ff275245ac05e4a2dfb38527"
SRC_URI[sha256sum] = "6de716f7d6dbe31c67116ae8ed209e0f3a9dd2ab3ebe3514eead209ae935f689"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/grab ${D}/${bindir}
}

do_package_qa() {
}

FILES_${PN}  = "${bindir}/grab"
