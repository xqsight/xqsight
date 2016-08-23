package com.xqsight.upload.support;

import com.alibaba.fastjson.JSON;
import com.xiaoleilu.hutool.util.FileUtil;
import com.xqsight.commons.support.FileExtSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/6/14.
 */
public class VideoSupport {

    private Logger logger = LogManager.getLogger(VideoSupport.class);

    /**
     * mencoder.exe所放的路径
     **/
    @Value("${xqsight.file.vedio.mencoder.home}")
    private static String mencoder_home;

    /**
     * ffmpeg.exe所放的路径
     **/
    @Value("${xqsight.file.vedio.ffmpeg.home}")
    private static String ffmpeg_home;

    /**
     * 存放rm,rmvb等无法使用ffmpeg直接转换为flv文件先转成的avi文件
     **/
    private String tempFile_home;

    public VideoSupport(String tempFilePath) {
        this.tempFile_home = tempFilePath;
    }

    /**
     * 功能函数
     *
     * @param inputFile  待处理视频，需带路径
     * @param outputFile 处理后视频，需带路径
     * @return
     */
    public boolean convert(String inputFile, String outputFile, String mediaPicFile) {
        if (!FileUtil.isFile(inputFile)) {
            logger.info("{} is not file", inputFile);
            return false;
        }

        if (process(inputFile, outputFile,mediaPicFile)) {
            logger.info("covert ok");
            return true;
        }
        return false;
    }

    /**
     * 转换过程 ：先检查文件类型，在决定调用 processFlv还是processAVI
     *
     * @param inputFile
     * @param outputFile
     * @return
     */
    private boolean process(String inputFile, String outputFile, String mediaPicFile) {
        int type = FileExtSupport.checkIsConvertVedio(inputFile);
        boolean status = false;
        if (type == 0) {
            status = processFLV(inputFile, outputFile);// 直接将文件转为flv文件
            boolean flag = processIMG(inputFile,mediaPicFile);
            logger.info("转换FLV结果:{},转换IMG结果:{}", status,flag);
        } else if (type == 1) {
            String aviFilePath = processAVI(inputFile);
            logger.info("vedio covert avi filepath:{}", aviFilePath);
            if (StringUtils.isBlank(aviFilePath))
                return false;// avi文件没有得到
            status = processFLV(aviFilePath, outputFile);// 将avi转为flv
            boolean flag = processIMG(aviFilePath,mediaPicFile);
            logger.info("转换FLV结果:{},转换IMG结果:{}", status,flag);
        }
        return status;
    }

    /**
     * 从视屏文件获取图片
     *
     * @param inputFile
     * @param outputFile
     * @return
     */
    private boolean processIMG(String inputFile, String outputFile) {
        if (FileUtil.isFile(outputFile)) {
            logger.info("img文件已经存在,无需转换");
            return true;
        } else {
            logger.info("正在从视频中截取图片……");
            // 创建一个List集合来保存从视频中截取图片的命令
            List<String> cutpic = new ArrayList<String>();
            cutpic.add(ffmpeg_home);
            cutpic.add("-i");
            cutpic.add(inputFile);   // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
            cutpic.add("-y");
            cutpic.add("-f");
            cutpic.add("image2");
            cutpic.add("-ss");      // 添加参数＂-ss＂，该参数指定截取的起始时间
            cutpic.add("17");       // 添加起始时间为第17秒
            cutpic.add("-t");       // 添加参数＂-t＂，该参数指定持续时间
            cutpic.add("0.001");    // 添加持续时间为1毫秒
            cutpic.add("-s");       // 添加参数＂-s＂，该参数指定截取的图片大小
            cutpic.add("800*280");  // 添加截取的图片大小为350*240
            cutpic.add(outputFile); // 添加截取的图片的保存路径
            logger.info("ffmepg convert vedio to img commend is :{}", JSON.toJSONString(cutpic));
            try {
                ProcessBuilder builder = new ProcessBuilder();
                builder.command(cutpic);
                builder.redirectErrorStream(true);
                // 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
                //因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
                builder.start();
                return true;
            } catch (IOException e) {
                logger.error("ffmepg convert vedio to flv fail,reason:{}", e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
    }

    /**
     * ffmepg: 能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
     *
     * @param inputFile
     * @param outputFile
     * @return
     */
    private boolean processFLV(String inputFile, String outputFile) {
        if (FileUtil.isFile(outputFile)) {
            logger.info("flv文件已经存在,无需转换");
            return true;
        } else {
            logger.info("正在转换成flv文件……");
            List<String> commend = new ArrayList<String>();
            commend.add(ffmpeg_home);  // 添加转换工具路径
            commend.add("-i");         // 添加参数＂-i＂，该参数指定要转换的文件
            commend.add(inputFile);    // 添加要转换格式的视频文件的路径
            commend.add("-ab");        // 设置音频码率
            commend.add("128");
            commend.add("-acodec");
            commend.add("libmp3lame");
            commend.add("-ac");        //设置声道数
            commend.add("1");
            commend.add("-ar");        //设置声音的采样频率
            commend.add("22050");
            commend.add("-r");         //设置帧频
            commend.add("29.97");
            commend.add("-qscale");      //指定转换的质量  -qscale 4 为最好但文件大, -qscale 6就可以了
            commend.add("4");
            commend.add("-y");         // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
            commend.add(outputFile);
            logger.info("ffmepg convert vedio to flv commend is :{}", JSON.toJSONString(commend));
            try {
                ProcessBuilder builder = new ProcessBuilder();
                builder.command(commend);
                builder.redirectErrorStream(true);
                // 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
                //因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
                builder.start();
                return true;
            } catch (IOException e) {
                logger.error("ffmepg convert vedio to flv fail,reason:{}", e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
    }

    /**
     * Mencoder:
     * 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
     *
     * @param inputFile
     * @return
     */
    private String processAVI(String inputFile) {
        List<String> commend = new ArrayList<String>();
        commend.add(mencoder_home);
        commend.add(inputFile);
        commend.add("-oac");
        commend.add("mp3lame");
        commend.add("-lameopts");
        commend.add("preset=64");
        commend.add("-ovc");
        commend.add("xvid");
        commend.add("-xvidencopts");
        commend.add("bitrate=600");
        commend.add("-of");
        commend.add("avi");
        commend.add("-o");
        commend.add(tempFile_home);
        logger.info("Mencoder convert vedio commend is :{}", JSON.toJSONString(commend));
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commend);
            Process p = builder.start();
            /**
             * 清空Mencoder进程 的输出流和错误流
             * 因为有些本机平台仅针对标准输入和输出流提供有限的缓冲区大小，
             * 如果读写子进程的输出流或输入流迅速出现失败，则可能导致子进程阻塞，甚至产生死锁。
             */
            final InputStream is1 = p.getInputStream();
            final InputStream is2 = p.getErrorStream();
            new Thread() {
                public void run() {
                    BufferedReader br = new BufferedReader(new InputStreamReader(is1));
                    try {
                        String lineB = null;
                        while ((lineB = br.readLine()) != null) {
                            if (lineB != null)
                                logger.debug(lineB);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

            new Thread() {
                public void run() {
                    BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
                    try {
                        String lineC = null;
                        while ((lineC = br2.readLine()) != null) {
                            if (lineC != null)
                                logger.debug(lineC);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            //等Mencoder进程转换结束，再调用ffmpeg进程
            p.waitFor();
            logger.error("Mencoder convert vedio success,path={}", tempFile_home);
            return tempFile_home;
        } catch (InterruptedException e) {
            logger.error("Mencoder convert vedio fail,reason:{}", e.getMessage());
            return null;
        } catch (IOException e) {
            logger.error("Mencoder convert vedio fail,reason:{}", e.getMessage());
            return null;
        }
    }
}
