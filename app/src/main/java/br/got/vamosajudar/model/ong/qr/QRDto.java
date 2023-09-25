package br.got.vamosajudar.model.ong.qr;

public class QRDto {

    private String qrcode64;
    private String brcode;

    public QRDto(String qrcode64, String brCode) {
        this.qrcode64 = qrcode64;
        this.brcode = brCode;
    }

    public String getQrcode64() {
        return qrcode64;
    }

    public String getBrcode() {
        return brcode;
    }

    @Override
    public String toString() {
        return "QRDto{" +
                "qrcode64='" + qrcode64 + '\'' +
                ", brCode='" + brcode + '\'' +
                '}';
    }
}
