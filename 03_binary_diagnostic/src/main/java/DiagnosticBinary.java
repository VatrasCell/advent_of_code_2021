import java.util.Objects;

public class DiagnosticBinary {

    private final String binary;

    public DiagnosticBinary(String binary) {
        this.binary = binary;
    }

    public String getBinary() {
        return binary;
    }

    public int getBit(int pos) {
        return Integer.parseInt(binary.split("")[pos]);
    }

    public int getLength() {
        return binary.length();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiagnosticBinary binary1 = (DiagnosticBinary) o;
        return Objects.equals(binary, binary1.binary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(binary);
    }
}
