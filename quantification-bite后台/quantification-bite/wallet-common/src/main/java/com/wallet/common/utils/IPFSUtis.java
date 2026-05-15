package com.wallet.common.utils;
import com.wallet.common.utils.file.MultipartFileToFile;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
//import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class IPFSUtis {
    /**
     * http://43.129.71.63:5001/webui
     * ipfs的服务器地址和端口,替换成自己的ip，port
     */
    private static IPFS IPFS = null;

    public IPFSUtis(String multiaddr){
        IPFS = new IPFS(multiaddr);
    }

    public static String upload(String fileName) throws IOException {
        NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new File(fileName));
        MerkleNode addResult = IPFS.add(file).get(0);
        return addResult.hash.toString();
    }

    public static String upload(MultipartFile multipartFile) throws Exception {
        File newFile = MultipartFileToFile.multipartFileToFile(multipartFile);
        NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(newFile);
        MerkleNode addResult = IPFS.add(file).get(0);
        return addResult.hash.toString();
    }

    public static String uploadInputStream(InputStream inputStream,String name) throws Exception {
        File newFile = MultipartFileToFile.test(inputStream,name);
        NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(newFile);
        MerkleNode addResult = IPFS.add(file).get(0);
        return addResult.hash.toString();
    }

    public static String upload(byte[] data) throws IOException {
        NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper(data);
        MerkleNode addResult = IPFS.add(file).get(0);
        return addResult.hash.toString();
    }

    public static byte[] getBytesByFile(String filePath) {
        try {
            File file=new File(filePath);
            //获取输入流
            FileInputStream fis = new FileInputStream(file);

            //新的 byte 数组输出流，缓冲区容量1024byte
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
            //缓存
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            //改变为byte[]
            byte[] data = bos.toByteArray();
            //
            bos.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] download(String hash) {
        byte[] data = null;
        try {
            data = IPFS.cat(Multihash.fromBase58(hash));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void download(String hash, String destFile) {
        byte[] data = null;
        try {
            data = IPFS.cat(Multihash.fromBase58(hash));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (data != null && data.length > 0) {
            File file = new File(destFile);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                fos.write(data);
                fos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

//    public static void main(String[] args) {
//        try {
//            String str = "D:/image/1.jpg";
//            File pdfFile = new File(str);
//            FileInputStream fileInputStream = new FileInputStream(pdfFile);
//            MultipartFile multipartFile = new MockMultipartFile(pdfFile.getName(), pdfFile.getName(),
//                    ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
//            String url = IPFSUtis.upload(multipartFile);
//            System.out.println(url);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}
