package io.github.rothschil.common.down.bo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileList {

    private static final long serialVersionUID = 3347631520408163595L;

    public FileList() {
    }


    public FileList(String transId, String fileName, String remotePath, String localPath, Long id) {
        this.transId = transId;
        this.fileName = fileName;
        this.remotePath = remotePath;
        this.localPath = localPath;
        this.id = id;
    }

    /**
     * 文件列表标识
     */
    private Long id;

    /**
     * 流水标识，YYYYMMDDHH24MISS+8位随机数字
     */
    private String transId;

    /**
     * 系统名称
     */
    private String fileName;

    /**
     * 文件入库时间
     */
    private LocalDateTime inputTime;

    /**
     * 远程文件所在路径
     */
    private String remotePath;

    /**
     * 本地文件所在路径
     */
    private String localPath;

    /**
     * 文件大小（kb）
     */
    private String fileSize;

    /**
     * 状态
     */
    private Integer status;


}
