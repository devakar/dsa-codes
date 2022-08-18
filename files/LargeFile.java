class Solution {
    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile("C:\\data.txt", "r");
        long length = file.length();
        long offset = 0;
        long size = 100 * 1024 * 1024; // read by 100Mb

        FileChannel channel = file.getChannel();
        while (offset < length) {
            file.seek(offset);
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, offset, Math.min(size, length - offset));
            buffer.load();
            for (int i = 0; i < buffer.limit(); i++) {
                // index: i + offset
                // value: c
                char c = (char) buffer.get();
                // System.out.print(c);
            }
            buffer.clear();
            offset += size;
        }
        channel.close();
        file.close();
    }
}