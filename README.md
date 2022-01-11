# Unable to start foreground service on Android 12

The described issue with Sticky Foreground service on Android 12.

## Scenario
Suppose we have an application that starts the foreground service at startup. Then we want a worker to start after 8 seconds via WorkManager to do a task.

## Problem
What happens when the app is in the background? 

The application crashes or for some reason, the system kills the process. Because we have a STICKY foreground service, the system will restart the service after a certain amount of time.
Here is the catch. If the scheduled worker is triggered before the system restarts the foreground service, the app will start instead. When started, the system thinks "Oh, the app started, but it's idle (in the background), I will stop this foreground service I remembered this app has". Then the app started this way is technically "started from the background" and is not allowed to start a foreground service. The app tries to start it in the foreground, gets an exception, and crashes.

## Requirements
- Android 12
- Compile and Targed SDK 31


## To reproduce

### Case 1 (master branch)
1. Run the app.
2. Terminate the app at least 3x in a row from the Android Studio - Logcat tab (Terminate button). Remember, if foreground service is killed, there is a certain amount of time, when service can be restarted. (1s, 4, 16s, ..., 64s). You need to wait for the system to restart the app and then kill the process again.
3. After the third termination and restart of the service, open the app and start a worker.
4. Then terminate the app again (Terminate button) and wait for the worker. Catch the exception to prevent crashing the app, but in that case, the STICKY service won't be restarted after a certain amount of time.

### Case 2 (bug/the-worker-causes-an-app-crash)
1. Run the app.
2. Terminate the app at least 3x in a row from the Android Studio - Logcat tab (Terminate button). Remember, if foreground service is killed, there is a certain amount of time, when service can be restarted. (1s, 4, 16s, ..., 64s). You need to wait for the system to restart the app and then kill the process again.
3. After the third termination and restart of the service, open the app and start a worker.
4. Then terminate the app again. (Terminate button) and the worker will cause an app crash.

```
Caused by: android.app.ForegroundServiceStartNotAllowedException: startForegroundService() not allowed due to mAllowStartForeground false: service {service_name}
```

# Kudos
Thanks to [Grega](https://github.com/muscardinus), who discovered the issue and helped me with the case.
