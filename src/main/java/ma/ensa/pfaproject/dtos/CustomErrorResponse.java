package ma.ensa.pfaproject.dtos;


import lombok.Data;

@Data
public class CustomErrorResponse {
    private String resourceType;
    private Long resourceId;
    private String detailedMessage;

    public CustomErrorResponse(String resourceType, Long resourceId, String detailedMessage) {
        this.resourceType = resourceType;
        this.resourceId = resourceId;
        this.detailedMessage = detailedMessage;
    }
    public CustomErrorResponse(String resourceType,String detailedMessage) {
        this.resourceType = resourceType;
        this.detailedMessage = detailedMessage;
    }


    // Getters et setters pour les attributs
}