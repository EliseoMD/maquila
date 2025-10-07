package ws.maquila.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "opinion")
public class Opinion{
	@Field("id")
    private Integer id;
    @Field("text")
    private String text;
    @Field("polarity")
    private String polarity;
    @Field("created")    
    private Date created;    
}
