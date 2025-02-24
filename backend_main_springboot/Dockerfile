FROM gradle:8.5-jdk21

WORKDIR /app
EXPOSE 8080

# Configuración optimizada para desarrollo
ENV GRADLE_OPTS="-Dorg.gradle.daemon=true -Dorg.gradle.parallel=true"

# Comando para ejecutar en modo continuo para mejor detección de cambios
CMD ["gradle", "bootRun", "--continuous", "--args='--spring.devtools.restart.enabled=true'"]
