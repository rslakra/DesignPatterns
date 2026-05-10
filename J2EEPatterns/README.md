# J2EEPatterns

Legacy **J2EE / servlet / JSP** examples (original `com.devamatre…`, O’Reilly book samples under `com.oreilly…`, etc.). This tree is built with **Maven** only; the old Ant `build.xml` at this level has been removed.

## Requirements

- **Maven** 3.6+
- **JDK 21** — these modules use **`source` / `target` 21** (see [`J2EEPatterns/pom.xml`](pom.xml)), matching the rest of the reactor.
- **javax.\*** APIs (Servlet, JSP, Java EE 8, CORBA for `javax.rmi`, etc.) are declared as managed dependencies in the parent POM.

## Build

From this directory:

```bash
cd J2EEPatterns
mvn clean package -Drevision=0.0.1-SNAPSHOT
```

From the **repository root** (full reactor):

```bash
mvn clean package -Drevision=0.0.1-SNAPSHOT
```

WARs use each module’s `finalName` (e.g. `j2ee-view-helper.war` under `ViewHelper/target/`). **O’Reilly** samples and **ResourcePool** produce JARs.

## Modules

| Directory | Artifact (prefix `j2ee-`) | Packaging |
|-----------|---------------------------|-----------|
| `AntiPatterns` | `antipatterns` | WAR (`webapp/` as web root) |
| `AsynchPage` | `asynch-page` | WAR |
| `CachingFilter` | `caching-filter` | WAR |
| `CompositeView` | `composite-view` | WAR |
| `Decorator` | `decorator` | WAR |
| `FrontController` | `front-controller` | WAR |
| `MVC` | `mvc` | WAR |
| `OReilly` | `oreilly-samples` | JAR |
| `ResourcePool` | `resource-pool` | JAR |
| `S2W` | `s2w` | WAR |
| `ViewHelper` | `view-helper` | WAR |

Java sources stay in each module’s **`src/`** tree; web assets sit next to **`WEB-INF/`** (or under **`webapp/`** for AntiPatterns). Checkstyle is **skipped** for this aggregator so historical style does not block builds.

---

## O’Reilly *J2EE Design Patterns* sample code

*(Merged from the original `README.TXT`.)*

Sample code supporting the book **J2EE Design Patterns** (O’Reilly).

- Copyright (C) 2003 **O’Reilly and Associates**
- [java.oreilly.com](http://java.oreilly.com)
- [Book catalog](http://www.oreilly.com/catalog/j2eedp/)

**How the book packaged the samples**

The code was split into three kinds of layout:

1. **Chapters 3, 4, and 5** — top-level Java packages with small web applications.
2. **Chapters 7–11 (business tier)** — under the `com.oreilly.patterns.*` package tree (see the **`OReilly/`** module here).
3. **Chapter 12 (Antipatterns)** — NetBeans-oriented layout to simplify EJB deployment (see **`AntiPatterns/`** in this repo).

— *William Crawford and Jonathan Kaplan*

---

## Chapter 12 — AntiPatterns (NetBeans / JSTL note)

*(Merged from the original `README copy.txt`; paths updated for this repository.)*

The Chapter 12 examples were provided in **NetBeans** form for easier deployment. In this repo the layout differs slightly from the original disk layout: EJB sources and descriptors live under **`AntiPatterns/src/.../antipatterns/ejbs`**, and the web tier under **`AntiPatterns/webapp`**. To approximate the old NetBeans setup, mount or import (under **`J2EEPatterns/`**):

- `AntiPatterns` (module root)
- `AntiPatterns/webapp`
- EJB packages: `AntiPatterns/src/com/devamatre/j2eepatterns/antipatterns/ejbs` (as an EJB / source root in the IDE if required)

You can then deploy the application referred to as **PersonApp** (e.g. `PersonApp` / `PersonApp.appasm` assets under `AntiPatterns/`).

You may need to add **JSTL** JARs under `AntiPatterns/webapp/WEB-INF/lib`, as described in any README under that `WEB-INF` tree.

For a **Maven** build of the WAR, use `mvn package` from the repo root or from `J2EEPatterns/`; the packaged web app is produced under `AntiPatterns/target/`.

### Run samples locally (`runMaven.sh`)

Each WAR module includes **`runMaven.sh`**: it sources **`../../version.sh`** from the repository root, then runs **`mvn clean package jetty:run-war`** so Jetty deploys the built WAR (EE8 / `javax.servlet`). Every sample uses **port 8080**; run **one module at a time** so the port is free.

| Module | Script | URL (after start) |
|--------|--------|-------------------|
| `AntiPatterns` | [`AntiPatterns/runMaven.sh`](AntiPatterns/runMaven.sh) | http://localhost:8080/antipatterns/ |
| `AsynchPage` | [`AsynchPage/runMaven.sh`](AsynchPage/runMaven.sh) | http://localhost:8080/asynch-page/ |
| `CachingFilter` | [`CachingFilter/runMaven.sh`](CachingFilter/runMaven.sh) | http://localhost:8080/caching-filter/ |
| `CompositeView` | [`CompositeView/runMaven.sh`](CompositeView/runMaven.sh) | http://localhost:8080/composite-view/ |
| `Decorator` | [`Decorator/runMaven.sh`](Decorator/runMaven.sh) | http://localhost:8080/decorator/ |
| `FrontController` | [`FrontController/runMaven.sh`](FrontController/runMaven.sh) | http://localhost:8080/front-controller/ |
| `MVC` | [`MVC/runMaven.sh`](MVC/runMaven.sh) | http://localhost:8080/mvc/ |
| `S2W` | [`S2W/runMaven.sh`](S2W/runMaven.sh) | http://localhost:8080/s2w/ |
| `ViewHelper` | [`ViewHelper/runMaven.sh`](ViewHelper/runMaven.sh) | http://localhost:8080/view-helper/ |
| `ResourcePool` | [`ResourcePool/runMaven.sh`](ResourcePool/runMaven.sh) | Runs `PoolTest` via `exec:java` (console demo, not HTTP) |
| `OReilly` | [`OReilly/runMaven.sh`](OReilly/runMaven.sh) | Builds the JAR only (no bundled web app) |

Example:

```bash
cd J2EEPatterns/MVC
chmod +x runMaven.sh   # once, if needed
./runMaven.sh
```

Stop Jetty with **Ctrl+C**.

**Note (AntiPatterns):** This sample originally targeted **EJB** deployment (PersonApp). Jetty serves the **web** tier only; full EJB behavior requires an application server (GlassFish, WildFly, etc.) and the original NetBeans deployment flow described above.
