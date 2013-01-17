/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.handler.codec.spdy;

import io.netty.util.internal.StringUtil;

/**
 * The default {@link SpdyGoAwayFrame} implementation.
 */
public class DefaultSpdyGoAwayFrame implements SpdyGoAwayFrame {

    private final int lastGoodStreamId;
    private final SpdySessionStatus status;

    /**
     * Creates a new instance.
     *
     * @param lastGoodStreamId the Last-good-stream-ID of this frame
     */
    public DefaultSpdyGoAwayFrame(int lastGoodStreamId) {
        this(lastGoodStreamId, 0);
    }

    /**
     * Creates a new instance.
     *
     * @param lastGoodStreamId the Last-good-stream-ID of this frame
     * @param statusCode       the Status code of this frame
     */
    public DefaultSpdyGoAwayFrame(int lastGoodStreamId, int statusCode) {
        this(lastGoodStreamId, SpdySessionStatus.valueOf(statusCode));
    }

    /**
     * Creates a new instance.
     *
     * @param lastGoodStreamId the Last-good-stream-ID of this frame
     * @param status           the status of this frame
     */
    public DefaultSpdyGoAwayFrame(int lastGoodStreamId, SpdySessionStatus status) {
        if (lastGoodStreamId < 0) {
            throw new IllegalArgumentException("Last-good-stream-ID"
                    + " cannot be negative: " + lastGoodStreamId);
        }
        this.lastGoodStreamId = lastGoodStreamId;
        this.status = status;
    }

    @Override
    public int lastGoodStreamId() {
        return lastGoodStreamId;
    }

    @Override
    public SpdySessionStatus status() {
        return status;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(getClass().getSimpleName());
        buf.append(StringUtil.NEWLINE);
        buf.append("--> Last-good-stream-ID = ");
        buf.append(lastGoodStreamId);
        buf.append(StringUtil.NEWLINE);
        buf.append("--> Status: ");
        buf.append(status.toString());
        return buf.toString();
    }
}