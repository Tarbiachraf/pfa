package ma.ensa.pfaproject.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class RessourceNotFoundException extends RuntimeException{
    private String resourceType;
    private String resourceName;
    private Long resourceId;
    private String errorMessage;
    private LocalDateTime timestamp;
    public RessourceNotFoundException(String resourceType, Long resourceId, String detailedMessage) {
        super(detailedMessage);
        this.resourceType = resourceType;
        this.resourceId = resourceId;
        this.errorMessage = detailedMessage;
        this.timestamp = LocalDateTime.now();
    }
    public RessourceNotFoundException(String resourceType, String resourceName, String detailedMessage) {
        super(detailedMessage);
        this.resourceType = resourceType;
        this.resourceName = resourceName;
        this.errorMessage = detailedMessage;
        this.timestamp = LocalDateTime.now();
    }

}
