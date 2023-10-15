FROM ubuntu:latest
ARG DEBIAN_FRONTEND=noninteractive
ARG TOKEN=set
RUN apt-get update && apt-get install -y curl libgtk-dotnet3.0-cil
ENV RUNNER_ALLOW_RUNASROOT=1

RUN mkdir actions-runner && cd actions-runner
#Download the latest runner package
RUN curl -o actions-runner-linux-x64-2.310.2.tar.gz -L https://github.com/actions/runner/releases/download/v2.310.2/actions-runner-linux-x64-2.310.2.tar.gz
RUN tar xzf ./actions-runner-linux-x64-2.310.2.tar.gz
# Create the runner and start the configuration experience
RUN ./config.sh --url https://github.com/hgabor83/EmployeemanagerMicronaut --token TOKEN
RUN ./run.sh