package test.groovy
import crypto.algorithms.Algorithm
import crypto.algorithms.Cipher
import crypto.algorithms.DoubleAlgo
import crypto.algorithms.ReverseAlgo
import spock.lang.Shared
import spock.lang.Specification


class AlgoTesting extends Specification {
    @Shared final String CAESAR_NAME = "CAESAR_CIPHER";
    @Shared final String XOR_NAME = "XOR_CIPHER";
    @Shared final String MULTI_NAME = "MULTIPLICATIVE_CIPHER";


    @Shared Algorithm cipher
    @Shared Algorithm doubleAlgo
    @Shared Algorithm reverseAlgo
    @Shared byte[] plainText
    @Shared byte[] cipherText


    def "Test Caesar encryption"() {
        given:
        cipher = new Cipher((byte) 3, CAESAR_NAME)
        plainText = "Welcome to encryptor program".getBytes()

        when:
        cipherText = cipher.encrypt(plainText)

        then:
        new String(cipherText) == "Zhofrph#wr#hqfu|swru#surjudp"
    }


    def "Test Caesar decryption"() {
        given:
        cipher = new Cipher((byte) 7, CAESAR_NAME)
        cipherText = "Jhlzhy kljyfwapvu wyvjlzz".getBytes()

        when:
        plainText = cipher.decrypt(cipherText)

        then:
        new String(plainText) == "Caesar\u0019decr_pZion\u0019process"
    }


    def "Test Xor encryption"() {
        given:
        cipher = new Cipher((byte) 10, XOR_NAME)
        plainText = "Xor cipher process".getBytes()

        when:
        cipherText = cipher.encrypt(plainText)

        then:
        new String(cipherText) == "Rex*iczbox*zxeioyy"
    }


    def "Test xor decryption"() {
        given:
        cipher = new Cipher((byte) 10, XOR_NAME)
        cipherText = "ne*se\u007F*}kd~*~e*noixsz~*go5".getBytes()

        when:
        plainText = cipher.decrypt(cipherText)

        then:
        new String(plainText) == "do you want to decrypt me?"
    }


    def "Test multiplicative encryption"() {
        given:
        cipher = new Cipher((byte) 25, MULTI_NAME)
        plainText = "Multiplicative encryption process".getBytes()

        when:
        cipherText = cipher.encrypt(plainText)

        then:
        new String(cipherText) == "\u0014\u0004!jU\u0006!U>\fjU\u001Dp&pS>8h\u0006jUlS&\u00068l>pQQ"
    }


    def "Test multiplicative decryption"() {
        given:
        cipher = new Cipher((byte) 25, MULTI_NAME)
        cipherText = "\u0014\u0004!jU\u0006!U>\fjU\u001Dp&pS>8h\u0006jUlS&\u00068l>pQQ".getBytes()

        when:
        plainText = cipher.decrypt(cipherText)

        then:
        new String(plainText) == "Multiplicative encryption process"
    }


    def "Test Reverse of Caesar encryption"() {
        given:
        reverseAlgo = new ReverseAlgo(new Cipher((byte) 3, CAESAR_NAME))
        plainText = "Welcome to encryptor program".getBytes()

        when:
        cipherText = reverseAlgo.encrypt(plainText)

        then:
        new String(cipherText) == "Tbi`ljb\u001Dql\u001Dbk`ovmqlo\u001Dmoldo^j"
    }


    def "Test Reverse of Caesar decryption"() {
        given:
        reverseAlgo = new ReverseAlgo(new Cipher((byte) 3, CAESAR_NAME))
        cipherText = "Tbi`ljb\u001Dql\u001Dbk`ovmqlo\u001Dmoldo^j".getBytes()

        when:
        plainText = reverseAlgo.decrypt(cipherText)

        then:
        new String(plainText) == "Welcome to encryptor program"
    }


    def "Test Double of Caesar and Xor encryption"() {
        given:
        doubleAlgo = new DoubleAlgo(new Cipher((byte) 3, CAESAR_NAME), new Cipher((byte) 10, XOR_NAME))
        plainText = "Welcome to encryptor program".getBytes()

        when:
        cipherText = doubleAlgo.encrypt(plainText)

        then:
        new String(cipherText) == "Pbelxzb)}x)b{lvy}x)yx`nz"
    }


    def "Test Double of Caesar and Xor decryption"() {
        given:
        doubleAlgo = new DoubleAlgo(new Cipher((byte) 3, CAESAR_NAME), new Cipher((byte) 10, XOR_NAME))
        cipherText = "Pbelxzb)}x)b{lvy}x)yx`nz".getBytes()

        when:
        plainText = doubleAlgo.decrypt(cipherText)

        then:
        new String(plainText) == "Welcome to encryptor program"
    }
}

