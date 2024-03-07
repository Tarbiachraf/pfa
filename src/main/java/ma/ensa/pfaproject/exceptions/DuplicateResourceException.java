package ma.ensa.pfaproject.exceptions;

import java.time.LocalDateTime;

public class DuplicateResourceException extends RuntimeException{
    private String ressourceName;

    private Long ressourceId;

    private String resourceType;
    private String errorMessage;
    private LocalDateTime timestamp;

    public DuplicateResourceException(String resourceType,String ressourceName ,String errorMessage) {
        super(errorMessage);
        this.ressourceName =ressourceName;
        this.resourceType = resourceType;
        this.errorMessage = errorMessage;
        this.timestamp = LocalDateTime.now();
    }
    public DuplicateResourceException(String resourceType,Long ressourceId ,String errorMessage) {
        super(errorMessage);
        this.ressourceId =ressourceId;
        this.resourceType = resourceType;
        this.errorMessage = errorMessage;
        this.timestamp = LocalDateTime.now();
    }




    public String getResourceType() {
        return resourceType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
