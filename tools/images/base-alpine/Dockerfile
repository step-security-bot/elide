ARG ALPINE_VERSION=3.18.2
ARG ALPINE_HASH="@sha256:25fad2a32ad1f6f510e528448ae1ec69a28ef81916a004d3629874104f8a7f70"

FROM alpine:${ALPINE_VERSION}${ALPINE_HASH}

LABEL org.opencontainers.image.vendor=Elide
LABEL org.opencontainers.image.title="Elide Base (Alpine)"
LABEL org.opencontainers.image.description="Base image for Elide apps and tools built on top of musl-libc"
LABEL org.opencontainers.image.version=v3-alpha1
LABEL org.opencontainers.image.url=https://github.com/elide-dev/v3
LABEL org.opencontainers.image.base.name=alpine:${ALPINE_VERSION}
LABEL org.opencontainers.image.source=https://github.com/elide-dev/v3/blob/v3/tools/images/base-alpine/Dockerfile

RUN apk add --no-cache --update libstdc++ musl

ENV LC_CTYPE=en_US.UTF-8 \
  LC_ALL=en_US.UTF-8 \
  PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/usr/lib/gvm/bin:/sbin:/bin
