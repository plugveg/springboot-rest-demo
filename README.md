# Springboot REST demo - Thomas 'Setsuma' Siest - M1 CYBER

# BE CAREFULL

You may need to enable execution rights on .sh files via this command :
```bash
chmod +x [name_of_file].sh
```

---

When running, swagger is available : http://localhost:8080/swagger-ui/index.html

## Debug

VM Args have to be added 

```json
-Dspring.profiles.active=dev -Dspring.config.location=<PATH_TO_PROJECT>/springboot-rest-demo-config/src/main/resources/springboot-rest-demo.yml
```

### On VSCode

```json
        {
            "type": "java",
            "name": "DemoRestApplication",
            "request": "launch",
            "mainClass": "org.setsuma.springboot.demorest.DemoRestApplication",
            "projectName": "springboot-rest-demo-ws",
            "vmArgs": "-Dspring.profiles.active=dev -Dspring.config.location=<PATH_TO_PROJECT>springboot-rest-demo-config\\src\\main\\resources\\springboot-rest-demo.yml"
        }
```