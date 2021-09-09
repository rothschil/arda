package gvy02


class Example {
    def readFile() {
        File file = new File("E://file.txt");
        FileReader fr = new FileReader(file)
        def red = 0
        while ((red = fr.read()) != -1) {
            println((char) red)
        }
    }
}

