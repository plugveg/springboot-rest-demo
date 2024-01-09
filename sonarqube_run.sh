# Exécuter cette commande seulement après avoir lancé Docker Desktop 
  # et seulement si le container SonarQube n'existe pas
# Lancer le script avec la commande : ./sonarqube_run.sh dans le terminal (bash de préférence)
docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest

# MDP par défaut : admin
# MDP changé : 4GgGt95HQx6RTE@o