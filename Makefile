_UID = $(shell id -u)
_GID = $(shell id -g)
SHELL := /bin/bash

_COMPOSE = UID=$(_UID) GID=$(_GID) docker-compose

# Dataflow parameters
ENV='acm-inter-staging'
PROFILE='vn_operation_authcenter_biz_customer_session'
GCS_PATH='gs://acm-dp-ocp-tmn-vn-qa-source-archive/vn_operation_authcenter/biz_customer_session'
BIGQUERY_PATH='acm-inter-staging:source_local_df_qa.vn_operation_authcenter_biz_customer_session'
TRIGGER_FILE='gs://acm-dp-ocp-tmn-vn-qa-source-archive/vn_operation_authcenter/biz_customer_session/2019/03/biz_customer_session_20190301_082145.sha256'
BIGQUERY_WRITE_MODE='append'

default: help

help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
.PHONY: help

run-package:  ## Run the data-processing application using the application package
	@echo "⭐️ Executing application package... ⭐️"
	@echo "Bigquery mode: $(BIGQUERY_WRITE_MODE)"
	@python target/dist/acm-dp-data-processing-1.0.dev0/dp_data_process --setup_file=setup.py \
    -env $(ENV) \
    -pf $(PROFILE) \
    -input $(GCS_PATH) \
    -bqp $(BIGQUERY_PATH) \
    -scheduler 'trigger' \
    -trigger $(TRIGGER_FILE) \
    -bqwm $(BIGQUERY_WRITE_MODE) \
    -direct_load false \
    —runner=DATAFLOW



run-image: ## Run the data-processing application using the docker image
	@echo "⭐️ Executing application container... ⭐️"
	@echo "Bigquery mode: $(BIGQUERY_WRITE_MODE)"
	@docker run --rm us.gcr.io/dataplatform-1363/acm.dp.data.processing:latest \
				-e "TZ=Asia/Bangkok" \
				-e "GOOGLE_APPLICATION_CREDENTIALS=/opt/service-account/acn-callcenter-dataflow.json" \
				"python dp_data_process --setup_file=/usr/src/app/setup.py \
				-env ${ENV} \
				-pf ${PROFILE} \
				-input ${INPUT} \
				-output ${OUTPUT} \
				-bqp ${BQ_PATH} \
				-bqw ${BQ_WRITE_MODE} \
				-scheduler ${SCHEDULER} \
				-trigger ${TRIGGER} \
				--runner ${RUNNER} \
				-direct_load ${DIRECT_LOAD} \
				-j ${JOB_NAME}"

build-package:  ## Build the data-processing application to the deployment package
	@pyb -v && \
	pyb -v && \
	pip install target/dist/acm-dp-data-processing-1.0.dev0 && \
	echo " ✅ The package has been built successfully" || echo " ❌ Failure, please ensure that the setup tasks has been executed"
.PHONY: build-packge

build-image:   ## Build the data-processing application to "docker" image
	@echo "🐳 Building production image... 🐳"
	$(_COMPOSE) build data-processing
.PHONY: build-image

publish-image:  ## Publish the data-processing docker image to the DataPlatform container registry
	@echo "🐳 Publishing production image... 🐳"
	$(_COMPOSE) push data-processing
.PHONY: publish-image

test:  ## Execute the Unit tests
	@echo "🧪 Execute the tests 🧪"
	@sbt clean coverage test coverageReport
.PHONY: test

setup: clean  ## Setting up the project and install all dependencies, please execute the activate command after finishing the project setup
	@echo "✅ Setting up the project "
	@virtualenv -p python2.7 venv
	@source ./venv/bin/activate && \
	pip install pybuilder && \
	pip install -r requirements.txt && \
	pip install docker-compose
	@echo "📣📣📣📣📣 Please execute the command below to activate the VENV shell. 📣📣📣📣📣"
	@echo "👉👉👉👉👉 source venv/bin/activate️ 👈👈👈👈👈"
.PHONY: setup

clean:  ## Clean the project by removing venv and target folder
	@echo "✅ Clean the project "
	@rm -rf venv
	@rm -rf target
.PHONY: clean

_activate_venv:
	@echo "✅ Activate virtual environment [VENV] "
	source venv/bin/activate