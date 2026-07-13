package com.github.zaneway.format.rfc.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

/**
 * 文件上传控制器:对外暴露 RFC 文档(及其他类型文件)的 HTTP 上传入口。
 *
 * 设计说明:
 * - 当前仅定义了接收 {@link MultipartFile} 的入口,后续应在此处串联
 *   {@code RfcTextParser} 进行解析,并将结果写入向量库 / 关系库。
 * - 上传文件类型理论上限定为 .txt 文本 RFC,但此处尚未做白名单校验,
 *   接入真实流量前必须补充 MIME / 扩展名校验,防止越权或恶意输入。
 */
@Controller
public class FileController {


  /**
   * 处理 /upload 请求:接收用户上传的 RFC 文本文件。
   *
   * @param file Spring MVC 解析后的多部分文件对象,不可为空
   */
  @RequestMapping("/upload")
  public void fileUpload(MultipartFile file){
    // 记录客户端上传的原始文件名,用于后续溯源 (RfcSource.fileName)
    String originalFilename = file.getOriginalFilename();


  }




}
