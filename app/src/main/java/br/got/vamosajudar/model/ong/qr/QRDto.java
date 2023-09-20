package br.got.vamosajudar.model.ong.qr;

public class QRDto {

    private String qrcode64;
    private String brCode;

    public QRDto(String qrcode64, String brCode) {
        this.qrcode64 = qrcode64;
        this.brCode = brCode;
    }

    public String getQrcode64() {
        return qrcode64;
    }

    public String getBrCode() {
        return brCode;
    }

    @Override
    public String toString() {
        return "QRDto{" +
                "qrcode64='" + qrcode64 + '\'' +
                ", brCode='" + brCode + '\'' +
                '}';
    }
}
