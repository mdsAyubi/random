const exec = require("child_process").exec;
const rmdir = require("rimraf");

const CHROME_SESSION_DIR = "/tmp/chrome_ss";
const CHROME_PROFILE_DIR = "/tmp/chrome_pf";
const CHROME_EXE_PATH =
  "/Applications/Google\\ Chrome.app/Contents/MacOS/Google\\ Chrome";
const CHROME_PATH = `${CHROME_EXE_PATH} --incognito --user-data-dir=${CHROME_SESSION_DIR} --profile-directory=${CHROME_PROFILE_DIR}`;

const FF_PROFILE_DIR = "/tmp/ff_pf";
const FF_SESSION_DIR = "/tmp/ff_ss";
const FIREPOX_PATH = `/Applications/Firefox.app/Contents/MacOS/firefox -profile ${FF_PROFILE_DIR}`;

const currentProcess = {
  chrome: "",
  ff: ""
};

///Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome https://www.google.com --incognito --user_data_dir=/tmp/chrome --profile-directory=/tmp/chrome_pf

const currentTab = {
  chrome: "",
  ff: ""
};

function startServer(browser, url) {
  if (browser == "chrome") {
    startChrome(url);
    return true;
  }

  if (browser == "firefox") {
    startFirefox(url);
    return true;
  }
  return false;
}

function stopServer(browser, url) {
  if (browser == "chrome") {
    stopChrome(url);
    return true;
  }

  if (browser == "firefox") {
    stopFirefox(url);
    return true;
  }
  return false;
}

function cleanup(browser) {
  if (browser == "chrome") {
    deleteChromeSession(CHROME_PROFILE_DIR, CHROME_SESSION_DIR);
    return true;
  }

  if (browser == "firefox") {
    deleteFFSession(FF_PROFILE_DIR);
    return true;
  }
  return false;
}

function getUrl(browser) {
  if (browser == "chrome") {
    return currentTab.chrome;
  }

  if (browser == "firefox") {
    return currentTab.ff;
  }
}

function startChrome(url) {
  const path = `${CHROME_PATH} ${url}`;
  console.log(path);
  const child = exec(path);
  child.stdout.on("data", function(data) {
    console.log("stdout: " + data);
  });
  child.stderr.on("data", function(data) {
    console.log("stdout: " + data);
  });
  child.on("close", function(code) {
    console.log("closing code: " + code);
  });
  currentProcess.chrome = child;
  currentTab.chrome = url;
  return true;
}

function startFirefox(url) {
  const child = exec(`${FIREPOX_PATH} ${url}`);
  child.stdout.on("data", function(data) {
    console.log("stdout: " + data);
  });
  child.stderr.on("data", function(data) {
    console.log("stdout: " + data);
  });
  child.on("close", function(code) {
    console.log("closing code: " + code);
  });
  currentProcess.ff = child;
  currentTab.ff = url;
  return true;
}

function stopChrome() {
  const child = currentProcess.chrome;
  if (child) {
    console.log("Killing chrome");
    child.kill();
    currentTab.chrome = "";
    return true;
  }
  return false;
}

function stopFirefox() {
  const child = currentProcess.ff;
  if (child) {
    console.log("Killing ff");
    child.kill();
    currentTab.ff = "";
    return true;
  }
  return false;
}

function deleteChromeSession(profileDir, sessionDir) {
  rmdir(profileDir, function(e) {
    if (e) {
      console.log("Could not delete chrome prpfile dir" + e);
    } else console.log("Deleted profile dir");
  });

  rmdir(sessionDir, function(e) {
    if (e) {
      console.log("Could not delete chrome sesion dir" + e);
    } else console.log("Deleted session dir");
  });
}

function deleteFFSession(profileDir) {
  rmdir(profileDir, function(e) {
    if (e) {
      console.log("Could not delete FF prpfile dir" + e);
    } else console.log("Deleted profile dir");
  });
}

module.exports = {
  startServer: startServer,
  stopServer: stopServer,
  cleanup: cleanup,
  getUrl: getUrl
};
