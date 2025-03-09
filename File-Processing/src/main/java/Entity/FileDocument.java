package Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "files")
public class FileDocument {

    @Id
    @Field("id")
    private String id;

    @Field("filename")
    private String fileName;

    @Field("contentType")
    private String contentType;

    @Field("size")
    private long size;

    @Field("data")
    private byte[] data;


}
