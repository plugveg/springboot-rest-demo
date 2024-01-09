# Copier ici, la commande donnée par SonarQube lors de la création du projet
# Analysis method => Locally with Maven
# Lancer le script avec la commande : ./sonarqube_mvn.sh dans le terminal (bash de préférence)

mvn clean verify sonar:sonar \
  -Dsonar.projectKey=SpringBootSetsuma \
  -Dsonar.projectName='SpringBootSetsuma' \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=sqp_cc42614b085ccdd0276c02a1ff6bdf991384b6a3