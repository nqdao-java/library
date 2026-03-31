package libraryManager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class SysSett {
    @Id
    @Column(name = "settingKey", length = 100, nullable = false)
    private String settingKey;

    @Column(name = "settingValue", nullable = false)
    private String settingValue;

    @Column(name = "description", length = 255)
    private String description;
}
