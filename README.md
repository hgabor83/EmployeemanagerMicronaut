## Employee Manager Micronaut example

I just created this project for the purpose of practicing and to try out Micronaut framework.

I compared the results with Spring Boot version:
- Micronaut version load time is 3.5s, Spring Boot version load time: 5.5s
- Memory consumption is the same
- In my opinion the autoconfiguration and the overall experience is better in Spring Boot

Added some example Github action workflows

//Docker has issues with Github actions, so jump to windows part
Used with local runner, which runs with Docker, you can find it in the dockerRunner folder
Start it (you can get the token from Github create new runner):
    This way, building the image will take as argument the token as following:
        docker build . -t github_actions_runner --build-arg TOKEN=<the-token>
    And run it as follows:
        docker run github_actions_runner

//Windows runner:
Download
We recommend configuring the runner under "\actions-runner". This will help avoid issues related to service identity folder permissions and long path restrictions on Windows.

## Create a folder under the drive root
$ mkdir actions-runner; cd actions-runner
## Download the latest runner package
$ Invoke-WebRequest -Uri https://github.com/actions/runner/releases/download/v2.310.2/actions-runner-win-x64-2.310.2.zip -OutFile actions-runner-win-x64-2.310.2.zip
## Optional: Validate the hash
$ if((Get-FileHash -Path actions-runner-win-x64-2.310.2.zip -Algorithm SHA256).Hash.ToUpper() -ne 'cc4c9e357345a33bd99834daaf2e051730305e0a4e33e5a6b47d39e6a93844e4'.ToUpper()){ throw 'Computed checksum did not match' }
## Extract the installer
$ Add-Type -AssemblyName System.IO.Compression.FileSystem ; [System.IO.Compression.ZipFile]::ExtractToDirectory("$PWD/actions-runner-win-x64-2.310.2.zip", "$PWD")
## Create the runner and start the configuration experience
$ ./config.cmd --url YOUR_REPO_URL --token YOUR_TOKEN
## Run it!
$ ./run.cmd