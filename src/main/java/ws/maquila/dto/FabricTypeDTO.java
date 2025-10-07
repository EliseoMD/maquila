package ws.maquila.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FabricTypeDTO {
    private Integer id;
    @NotBlank(message = "DTO: The content must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 5, message = "DTO: The content must be at most 5 characters, and has at least one character")
    private String name;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }    
}
